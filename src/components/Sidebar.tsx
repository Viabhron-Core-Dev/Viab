import React, { useState } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { NexusElement, INITIAL_SIDEBAR } from '../types';
import { Folder, Edit3, Trash2, Info } from 'lucide-react';

interface SidebarProps {
  isEditing: boolean;
  onEditToggle: () => void;
}

export const Sidebar = ({ isEditing, onEditToggle }: SidebarProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const [items, setItems] = useState<NexusElement[]>(INITIAL_SIDEBAR);
  const [config] = useState({
    thickness: 12,
    position: '50%',
    opacity: 0.4,
    color: '#ffffff'
  });

  const toggleSidebar = () => !isEditing && setIsOpen(!isOpen);

  return (
    <>
      {/* Handle */}
      <div 
        className="fixed right-0 h-32 w-4 z-[200] flex items-center justify-center cursor-pointer group"
        style={{ top: config.position, transform: 'translateY(-50%)' }}
        onMouseEnter={() => setIsOpen(true)}
      >
        <motion.div 
          className="h-full rounded-l-full"
          style={{ 
            width: config.thickness, 
            backgroundColor: config.color,
            opacity: isOpen ? 0.8 : config.opacity 
          }}
          layoutId="handle"
        />
      </div>

      {/* Sidebar Content */}
      <AnimatePresence>
        {isOpen && (
          <motion.div 
            initial={{ x: 300, opacity: 0 }}
            animate={{ x: 0, opacity: 1 }}
            exit={{ x: 300, opacity: 0 }}
            className="fixed right-0 top-0 h-full w-64 glass-dark z-[190] p-6 shadow-2xl flex flex-col"
            onMouseLeave={() => !isEditing && setIsOpen(false)}
          >
            <div className="flex items-center justify-between mb-8">
              <h2 className="text-xl font-bold flex items-center gap-2">
                Nexus <span className="text-[10px] px-1.5 py-0.5 bg-blue-500 rounded font-mono">SIDE</span>
              </h2>
              <button 
                onClick={onEditToggle}
                className={`p-2 rounded-lg transition-colors ${isEditing ? 'bg-blue-600' : 'hover:bg-white/10'}`}
              >
                <Edit3 size={18} />
              </button>
            </div>

            <div className="grid grid-cols-2 gap-4 overflow-y-auto flex-1 pb-20">
              {items.map((item) => (
                <motion.div
                  key={item.id}
                  layout
                  className={`flex flex-col items-center gap-2 p-3 rounded-xl transition-all ${
                    isEditing ? 'edit-mode-active z-30' : 'hover:bg-white/5 active:scale-95'
                  }`}
                >
                  <div 
                    className={`w-12 h-12 rounded-2xl flex items-center justify-center relative shadow-lg transition-transform ${isEditing ? 'element-lift' : ''}`}
                    style={{ backgroundColor: item.color || '#333' }}
                  >
                    {item.icon ? <item.icon size={24} /> : <Folder size={24} />}
                    
                    {isEditing && (
                      <div className="absolute -top-2 -right-2 flex flex-col gap-1 z-40">
                        <motion.button
                          initial={{ scale: 0 }}
                          animate={{ scale: 1 }}
                          className="bg-red-500 p-1 rounded-full text-white shadow-lg"
                          onClick={(e) => {
                            e.stopPropagation();
                            setItems(prev => prev.filter(i => i.id !== item.id));
                          }}
                        >
                          <Trash2 size={10} />
                        </motion.button>
                        <motion.button
                          initial={{ scale: 0 }}
                          animate={{ scale: 1 }}
                          className="bg-blue-500 p-1 rounded-full text-white shadow-lg shadow-blue-900/40"
                          onClick={(e) => {
                            e.stopPropagation();
                            alert(`Nexus App Info: ${item.name}`);
                          }}
                        >
                          <Info size={10} />
                        </motion.button>
                      </div>
                    )}
                  </div>
                  <span className="text-[10px] text-center font-medium opacity-80 truncate w-full">
                    {item.name}
                  </span>
                </motion.div>
              ))}
            </div>

            {isEditing && (
              <div className="absolute bottom-6 left-6 right-6 p-4 glass rounded-2xl text-[10px] border-blue-500/50">
                <span className="text-blue-400 font-bold uppercase block mb-1">Edit Mode Active</span>
                Drag to reposition. Actions like Volume Sliders can be assigned via the (Add) menu.
              </div>
            )}
          </motion.div>
        )}
      </AnimatePresence>
    </>
  );
};
